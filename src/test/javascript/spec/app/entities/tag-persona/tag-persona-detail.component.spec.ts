import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TagPersonaDetailComponent } from 'app/entities/tag-persona/tag-persona-detail.component';
import { TagPersona } from 'app/shared/model/tag-persona.model';

describe('Component Tests', () => {
  describe('TagPersona Management Detail Component', () => {
    let comp: TagPersonaDetailComponent;
    let fixture: ComponentFixture<TagPersonaDetailComponent>;
    const route = ({ data: of({ tagPersona: new TagPersona(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TagPersonaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TagPersonaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TagPersonaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tagPersona on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tagPersona).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
