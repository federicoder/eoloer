import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { StudioProfessionaleUpdateComponent } from 'app/entities/studio-professionale/studio-professionale-update.component';
import { StudioProfessionaleService } from 'app/entities/studio-professionale/studio-professionale.service';
import { StudioProfessionale } from 'app/shared/model/studio-professionale.model';

describe('Component Tests', () => {
  describe('StudioProfessionale Management Update Component', () => {
    let comp: StudioProfessionaleUpdateComponent;
    let fixture: ComponentFixture<StudioProfessionaleUpdateComponent>;
    let service: StudioProfessionaleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [StudioProfessionaleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(StudioProfessionaleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(StudioProfessionaleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(StudioProfessionaleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new StudioProfessionale(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new StudioProfessionale();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
